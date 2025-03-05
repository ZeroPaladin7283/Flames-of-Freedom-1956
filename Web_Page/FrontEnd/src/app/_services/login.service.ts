import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';


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
        const error = await response.text();
        throw new Error(`Error occured: ${response.status} - ${error}`);
      }

      const data = await response.json();
      console.log('User data with JWT token: ', data);
      
      localStorage.setItem('token', data.result.jwt);
      this.authenticatedSubject.next(true);

      return data;
    } catch (error) {
      console.error('Error logging in: ', error);
      throw error;
    }
  }

  logOut(): void {
    localStorage.removeItem('token');
    this.authenticatedSubject.next(false);
  }

  private tokenDecoder(token: string): any {
    try {
      return jwtDecode(token);
    } catch (error) {
      console.error('Error trying to decode JWT token: ', error);
      return null;
    }
  }

  getUserId(): number | null {
    const token = localStorage.getItem('token');
    if(!token) return null;

    const JWTToken = this.tokenDecoder(token);
    return JWTToken?.id ? parseInt(JWTToken.id, 10) : null;
  }

  getIsAdmin(): boolean {
    const token = localStorage.getItem('token');
    if(!token) return false;

    const JWTToken = this.tokenDecoder(token);
    return JWTToken?.isAdmin === true;
  }
}
