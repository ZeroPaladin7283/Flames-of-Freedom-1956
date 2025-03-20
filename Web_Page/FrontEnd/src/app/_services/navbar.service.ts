import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavbarService {

  private userUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/users`;

  constructor() { }

  async fetchUser(userId: number): Promise<any> {
    try {
      const response = await fetch(`${this.userUrl}/getUserById?id=${userId}`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
      });

      if (!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      return await response.json();
    } catch (error) {
      console.warn('Fetching user failed: ', error);
      throw error;
    }
  }
}
