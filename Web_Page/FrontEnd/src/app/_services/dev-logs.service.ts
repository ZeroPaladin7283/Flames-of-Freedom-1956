import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DevLogsService {
  private baseUrl = `https://api.github.com/users/`;
  constructor() {}

  fetchGitUser(gitUser: string): Promise<any> {
    const apiUrl = `${this.baseUrl}${gitUser}`;
    return fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .catch((error) => {
        console.error('Error fetching user data:', error);
        return null;
      });
  }
}
