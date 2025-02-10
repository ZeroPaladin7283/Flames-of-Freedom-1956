import { Component} from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { LoginService } from '../../_services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent{
  loginUser: string = "";
  loginPass: string = "";
  errorMessage: string = "";

  constructor(private loginService: LoginService, private router: Router) {}

  onLogin() {
    if (!this.loginUser || !this.loginPass) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    if(this.loginService.adminLoginFunc(this.loginUser, this.loginPass)) {
      this.router.navigate(['adminhome']);
    } else if(this.loginService.userLoginFunc(this.loginUser, this.loginPass)) {
      this.router.navigate(['logged-in-home']);
    } else {
      this.errorMessage = 'Invalid email or password. Please try again.';
    }
  }
}

