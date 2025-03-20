import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DownloadService {
  private reviewUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/review/getReviews`;

  constructor() { }

  fetchReviews(): Promise<any[]>{
    return fetch(this.reviewUrl)
    .then((response) => {
      if(!response.ok){
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      return data.result;
    })
    .catch((error) => {
      console.error('Error fetching reviews: ', error);
      return[];
    });
  }
}
