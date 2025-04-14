import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  private temporaryPassUrl = 'http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/users';

  constructor() { }

  async sendTemporaryPass(to: string, ccMe: boolean): Promise<any> {
    const verificationCreds = {to, ccMe};

    try {
      const response = await fetch(`${this.temporaryPassUrl}/temporaryPass`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(verificationCreds)
      });

      if(!response.ok) {
        const errorDetail = await response.text();
        console.error(`Error response: ${response.status} - ${errorDetail}`);
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      const responseData = await response.json();
      console.log("Email sent successfully: ", responseData);

      return responseData;
    } catch(error) {
      console.error('Message could not be sent:', error);
      throw error;
    }
  }

  async changePassword(emailIn: string, passwordIn: string): Promise<any> {
    const changeCreds = {emailIn, passwordIn};

    try {
      const response = await fetch(`${this.temporaryPassUrl}/changePassword`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(changeCreds)
      });

      if(!response.ok) {
        const errorDetail = await response.text();
        console.error(`Error response: ${response.status} - ${errorDetail}`);
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      const responseData = await response.json();
      console.log("Email sent successfully: ", responseData);

      return responseData;
    } catch(error) {
      console.error('Message could not be sent: ', error);
      throw error;
    }
  }
}
