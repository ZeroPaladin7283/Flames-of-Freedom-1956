import { Injectable } from '@angular/core';

interface User{
  email: string;
  password: string;
  username?: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private adminLogin: User[] = [
    { email: 'ZeroPaladin@gmail.com', password: 'admin'},
    { email: 'ShadowFoxy0819@gmail.com', password: 'ILoveFoxes'},
    { email: 'Döme@gmail.com', password: '1234' }
  ];

  private userLogin: User[] = [
    { email: 'jdoe@gmail.com' , password: 'Pass1234'},
    { email: 'kbrown@gmail.com' , password: 'abcDef12'},
    { email: 'tgreen@gmail.com' , password: 'zyxW9876'},
    { email: 'lwilson@gmail.com' , password: 'pasS5678'},
    { email: 'afoster@email.com' , password: 'passWord!1'},
    { email: 'phall@email.com' , password: '12345Abcd'},
    { email: 'cgarcia@email.com' , password: '7yUi90op'},
    { email: 'sroberts@gmail.com' , password: 'strongPwd34'},
    { email: 'mperry@gmail.com' , password: 'A1s2d3f4'},
    { email: 'bturner@gmail.com' , password: 'qWeasd789'},
    { email: 'dfoster@gmail.com' , password: 'zaq12wSx'},
    { email: 'nkhan@gmail.com' , password: 'blueSky99'},
    { email: 'mlopez@email.com' , password: 'tryHarder56'},
    { email: 'kpatel@gmail.com' , password: 'randomPass23'},
    { email: 'zroberts@gmail.com' , password: 'UlTiMaTe123'},
    { email: 'swhite@gmail.com' , password: 'FootBall4Life'},
    { email: 'bgreen@gmail.com' , password: 'baskeTball98'},
    { email: 'tthomas@gmail.com' , password: 'SunShine987'},
    { email: 'rmitchell@gmail.com' , password: 'Challenge56'},
    { email: 'pallen@gmail.com' , password: 'SecuRity12'},
    { email: 'egonzales@gmail.com' , password: 'Strong4Ever'},
    { email: 'jmartin@gmail.com' , password: 'Pa$$w0rd12'},
    { email: 'fyoung@gmail.com' , password: 'WinterisHere'}
  ]

  constructor() { }

  adminLoginFunc(email: string, password: string): boolean {
    return this.adminLogin.some(admin => admin.email === email && admin.password === password);
  }

  userLoginFunc(email: string, password: string): boolean {
    if(this.userLogin.some(user => user.email === email && user.password === password)) {
      return true;
    }

    const users: User[] = JSON.parse(localStorage.getItem('users') || '[]');

    return users.some(user => user.email === email && user.password === password);
  }
}
