import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor() { }

  public minDate = new Date('2009-01-01');

  registerFunc(username: string, email: string, password: string, cpassword: string, bdate: Date): boolean {
    return (username !== "" && email !== "" && password !== "" && cpassword !== "" && password === cpassword && bdate < this.minDate);
  }
}
