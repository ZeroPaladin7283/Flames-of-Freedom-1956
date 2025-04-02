import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {
  private postUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/posts/getPostData`;
  private deleteUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/posts/deletePost`;

  constructor() { }

  fetchPosts(): Promise<any[]>{
    return fetch(this.postUrl)
    .then((response) => {
      if(!response.ok) {
        throw new Error (`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      return data.result;
    })
    .catch((error) => {
      console.error('Error fetching post data: ', error);
      return[];
    });
  }

  async deletePost(id: number): Promise<any> {
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
      console.warn('Failed to delete post: ', error);
      throw error;
    }
  }
}
