import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private adminLogin = [
    { email: 'ZeroPaladin@gmail.com', password: 'admin'},
    { email: 'ShadowFoxy0819@gmail.com', password: 'ILoveFoxes'},
    { email: 'Döme@gmail.com', password: '1234' }
  ];

  private userLogin = [
    { email: 'jdoe@gmail.com' , password: 'pass1234'},
    { email: 'kbrown@gmail.com' , password: 'abcdef12'},
    { email: 'sroberts@gmail.com' , password: 'strongPwd34'},
    { email: 'mlopez@email.com' , password: 'tryHarder56'},
    { email: 'bgreen@gmail.com' , password: 'basketball98'},
  ]

  constructor() { }

  adminLoginFunc(email: string, password: string): boolean {
    return this.adminLogin.some(admin => admin.email === email && admin.password === password);
  }

  userLoginFunc(email: string, password: string): boolean {
    return this.userLogin.some(user => user.email === email && user.password === password);
  }
}
