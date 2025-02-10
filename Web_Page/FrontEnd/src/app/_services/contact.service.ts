import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private users = [
    { username: 'jdoe', email: 'jdoe@gmail.com'},
    { username: 'kbrown', email: 'kbrown@gmail.com'},
    { username: 'tgreen', email: 'tgreen@gmail.com'},
    { username: 'lwilson', email: 'lwilson@gmail.com'},
    { username: 'afoster', email: 'afoster@gmail.com'},
    { username: 'phall', email: 'phall@gmail.com'},
    { username: 'cgarcia', email: 'cgarcia@gmail.com'},
    { username: 'sroberts', email: 'sroberts@gmail.com'},
    { username: 'mperry', email: 'mperry@gmail.com'},
    { username: 'bturner', email: 'bturner@gmail.com'},
    { username: 'dfoster', email: 'dfoster@gmail.com'},
    { username: 'nkhan', email: 'nkhan@gmail.com'},
    { username: 'mlopez', email: 'mlopez@gmail.com'},
    { username: 'kpatel', email: 'kpatel@gmail.com'},
    { username: 'zroberts', email: 'zroberts@gmail.com'},
    { username: 'swhite', email: 'swhite@gmail.com'},
    { username: 'bgreen', email: 'bgreen@gmail.com'},
    { username: 'tthomas', email: 'tthomas@gmail.com'},
    { username: 'rmitchell', email: 'rmitchell@gmail.com'},
    { username: 'pallen', email: 'pallen@gmail.com'},
    { username: 'egonzales', email: 'egonzales@gmail.com'},
    { username: 'jmartin', email: 'jmartin@gmail.com'},
    { username: 'fyoung', email: 'fyoung@gmail.com'}
  ]

  constructor() { }

  contactFunc(username: string, email: string, message: string): boolean {
    return (this.users.some(user => user.username === username && user.email === email) && message != "");
  }
}
