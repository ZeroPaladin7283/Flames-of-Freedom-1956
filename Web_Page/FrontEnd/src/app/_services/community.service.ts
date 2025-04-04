import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommunityService {
  private postUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/posts/getAllPosts`;
  private deleteUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/posts/deletePost`;
  private createUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/posts/createPost`;
  private categoriesUrl = `http://127.0.0.1:8080/Flames_of_Freedom_1956-1.0-SNAPSHOT/webresources/categories/getCategories`;

  constructor() { }

  fetchPosts(): Promise<any[]>{
    return fetch(this.postUrl)
    .then((response) => {
      if (!response.ok) {
        throw new Error (`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      return data.result;
    })
    .catch((error) => {
      console.error('Error fetching post data: ', error);
      return [];
    });
  }

  async fetchCategories(): Promise<any[]> {
    try {
      const response = await fetch(this.categoriesUrl);
      if (!response.ok) {
        throw new Error(`Error fetching categories: ${response.status}`);
      }
      const data = await response.json();
      return data.result;
    } catch (error) {
      console.error('Error fetching categories: ', error);
      return [];
    }
  }

  async deletePost(id: number): Promise<any> {
    const deleteCreds = { id: id };

    try {
      const response = await fetch(`${this.deleteUrl}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(deleteCreds)
      });

      if (!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      return await response.json();
    } catch (error) {
      console.warn('Failed to delete post: ', error);
      throw error;
    }
  }

  async createPost(titleIn: string, categoryIdIn: number, contentIn: string, userIdIn: number) {
    const createCreds = { titleIn, categoryIdIn, contentIn, userIdIn };

    try {
      const response = await fetch(`${this.createUrl}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(createCreds)
      });

      if (!response.ok) {
        const errorDetail = await response.text();
        throw new Error(`Error: ${response.status} - ${errorDetail}`);
      }

      return await response.json();
    } catch (error) {
      console.warn('Failed to create post: ', error);
      throw error;
    }
  }
}
