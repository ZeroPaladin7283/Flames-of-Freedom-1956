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
  registerUsername: string = '';
  registerEmail: string = '';
  registerPass: string = '';
  registerCPass: string = '';
  registerBDate: string = '';
  errorMessage: string = '';

  constructor(private registerService: RegisterService) { }

  onRegister() {

    this.errorMessage = '';

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passRegex = /^(?!.*[s])(?=.*[A-Z])(?=.*\d).{8,24}$/;

    if (!this.registerUsername || !this.registerEmail || !this.registerPass || !this.registerCPass || !this.registerBDate) {
      this.errorMessage = 'Please fill in all fields!';
      return;
    }

    if (!emailRegex.test(this.registerEmail)) {
      this.errorMessage = 'Invalid email adress!';
      return;
    }

    if (!passRegex.test(this.registerPass)) {
      this.errorMessage = `Invalid password.
      Password must be 8-24 characters long,
      contain at least one uppercase letter,
      one number, and have no spaces.`;
      return;
    }

    if (this.registerPass !== this.registerCPass) {
      this.errorMessage = 'Passwords do not match!';
      return;
    }

    const parsedBDate = new Date(this.registerBDate);
    if (isNaN(parsedBDate.getTime()) || parsedBDate >= this.registerService.minDate) {
      this.errorMessage = 'You are too young to play this game!';
      return;
    }

    const isRegistered = this.registerService.registerFunc(
      this.registerUsername,
      this.registerEmail,
      this.registerPass,
      this.registerCPass,
      parsedBDate
    );

    if (isRegistered) {
      const users = JSON.parse(localStorage.getItem('users') || '[]');
      users.push({
        username: this.registerUsername,
        email: this.registerEmail,
        password: this.registerPass
      });

      localStorage.setItem('users', JSON.stringify(users));

      this.registerUsername = '';
      this.registerEmail = '';
      this.registerPass = '';
      this.registerCPass = '';
      this.registerBDate = '';

      alert('Registration successful!');
    } else {
      this.errorMessage = 'Invalid username, email or password!';
    }
  }
}
