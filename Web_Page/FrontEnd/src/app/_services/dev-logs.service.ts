import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DevLogsService {
  private logUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/logs/getAllLogs`;
  private deleteUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/logs/deleteLog`;
  private createUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/logs/createLog`;

  constructor() {}

  fetchLogsData(): Promise<any[]>{
    return fetch(this.logUrl)
    .then((response) => {
      if(!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      return data.result;
    })
    .catch((error) => {
      console.error('Error fetching logs data: ', error);
      return[];
    });
  }

  async deleteLog(id: number): Promise<any> {
    const deleteCreds = {id: id};

    try {
      const response = await fetch(`${this.deleteUrl}`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(deleteCreds)
      });

      if(!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      return await response.json();
    } catch(error) {
      console.warn('Failed to delete log: ', error);
      throw error;
    }
  }

  async createLog( logIn: string, adminIdIn: number) {
    const createCreds = { logIn, adminIdIn };

    try {
      const response = await fetch(`${this.createUrl}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(createCreds)
      });

      if(!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      return await response.json();
    } catch (error) {
      console.warn('Failed to create developer log: ', error);
      throw error;
    }
  }
}
