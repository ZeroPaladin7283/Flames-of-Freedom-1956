import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { JsonPipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private authenticatedSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(!!localStorage.getItem('token'));
  public authenticated$: Observable<boolean> = this.authenticatedSubject.asObservable();
  private url = 'http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/users';

  constructor() {}

  async login(email: string, password: string): Promise<any> {
    const loginCreds = { email, password };

    try {
      const response = await fetch(`${this.url}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginCreds)
      });

      if (!response.ok) {
        throw new Error(`Error: ${response.status}`);
      }

      const data = await response.json();
      console.log('User data with JWT token: ', data);

      localStorage.setItem('token', data.result.jwt);
      localStorage.setItem('loggedInUser', JSON.stringify(data.result));
      localStorage.setItem('userId', data.result.id);
      localStorage.setItem('isAdmin', data.result.isAdmin.toString())
      this.authenticatedSubject.next(true);

      return data;
    } catch (error) {
      console.warn('Server login failed, checking localStorage', error);

      const users = JSON.parse(localStorage.getItem('users') || '[]');
      const user = users.find((u: any) => u.email === email && u.password === password);

      if (!user) {
        throw new Error('Invalid email or password');
      }

      localStorage.setItem('loggedInUser', JSON.stringify(user));
      this.authenticatedSubject.next(true);

      return user;
    }
  }

  getIsAdmin(): boolean {
    const user = JSON.parse(localStorage.getItem('loggedInUser') || 'null');
    if(user) {
      return user.is_admin || false;
    }
    return false;
  }
  
  // private tokenDecoder(token: string): any {
  //   try {
  //     return jwtDecode(token);
  //   } catch (error) {
  //     console.error('Error trying to decode JWT token: ', error);
  //     return null;
  //   }
  // }
}