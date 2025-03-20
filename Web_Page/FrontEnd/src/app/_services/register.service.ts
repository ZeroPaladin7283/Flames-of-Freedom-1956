import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private userRegisterUrl = 'http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/users';
  public minDate = new Date('2009-01-01');

  constructor() { }

  async registerUser(username: string, email: string, password: string, dateOfBirth: string): Promise<any> {
    const registerCreds = { username, email, password, dateOfBirth};

    try {
      const response = await fetch(`${this.userRegisterUrl}/registerUser`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(registerCreds)
      });
    
      if (!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }
    
      return await response.json();
    } catch (error) {
      console.warn('Registration failed', error);
      throw error;
    }
  }
}
