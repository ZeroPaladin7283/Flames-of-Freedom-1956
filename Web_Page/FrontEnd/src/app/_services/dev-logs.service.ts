import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DevLogsService {
  private baseUrl = `https://api.github.com/users/`;
  private logUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/logs/getAllLogs`;

  constructor() {}

  fetchGitUser(userId: number): Promise<any> {
    const gitUser = userId === 16 ? 'PalcsiFerencKolos' : 'ZeroPaladin7283';
    return fetch(`${this.baseUrl}${gitUser}`)
    .then((response) => response.json())
    .catch((error) => {
      console.error('Error fetching Github user data: ', error);
      return null;
    });
  }

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
}
