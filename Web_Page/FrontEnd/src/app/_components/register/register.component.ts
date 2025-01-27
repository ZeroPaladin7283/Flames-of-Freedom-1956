import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { RegisterService } from '../../_services/register.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerUsername: string = "";
  registerEmail: string = "";
  registerPass: string = "";
  registerCPass: string = "";
  registerBDate: string = "";
  errorMessage: string = "";

  constructor(private registerService: RegisterService) {}

  onRegister() {

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if(!this.registerUsername || !this.registerEmail || !this.registerPass || !this.registerCPass || !this.registerBDate) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    if(emailRegex.test(this.registerEmail)) {
      this.errorMessage = 'Invalid email adress'
    }

    if(this.registerPass !== this.registerCPass) {
      this.errorMessage = 'Passwords do not match.';
      return;
    }

    const parsedBDate = new Date(this.registerBDate);
    if(isNaN(parsedBDate.getTime()) || parsedBDate >= this.registerService.minDate) {
      this.errorMessage = 'You are too young to play this game.';
      return;
    }

    const isRegistered = this.registerService.registerFunc(
      this.registerUsername,
      this.registerEmail,
      this.registerPass,
      this.registerCPass,
      parsedBDate
    );

    if(isRegistered) {
      localStorage.setItem('user', JSON.stringify({
        username: this.registerUsername,
        email: this.registerEmail,
        password: this.registerPass
      }));

      this.registerUsername = '';
      this.registerEmail = '';
      this.registerPass = '';
      this.registerCPass = '';
      this.registerBDate = '';
      this.errorMessage = '';

      alert('Registration successful!');
    } else {
      this.errorMessage = 'Invalid username, email or password!';
    }
  }
}
