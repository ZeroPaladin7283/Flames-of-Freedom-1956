import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DevLogsService {
  private baseUrl = `https://api.github.com/users/`;
  private logUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/logs/getAllLogs`;
  private deleteUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/logs/deleteLog`

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

  fetchGitUser(adminId: number): Promise<any> {
    const gitUser = adminId === 19 ? 'PalcsiFerencKolos' : 'ZeroPaladin7283';
    return fetch(`${this.baseUrl}${gitUser}`)
    .then((response) => response.json())
    .catch((error) => {
      console.error('Error fetching Github user data: ', error);
      return null;
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
}
