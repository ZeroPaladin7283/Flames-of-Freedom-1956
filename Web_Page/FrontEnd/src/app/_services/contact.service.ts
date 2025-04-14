import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private contactUrl = 'http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/users'

  constructor() { }

  async contactSupport(email: string, to: string, ccMe: boolean, subject: string, content: string): Promise<any> {
    const contactCreds = {email, to, ccMe, subject,  content };

    try {
      const response = await fetch(`${this.contactUrl}/sendEmail`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(contactCreds)
      });

      if(!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      return await response.json();
    }catch (error) {
      console.warn('Message could not be sent', error);
      throw error;
    }
  }
}
