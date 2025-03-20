import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { RegisterService } from '../../_services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  email: string = '';
  password: string = '';
  cpassword: string = '';
  dateOfBirth: Date = new Date('1111-01-01');
  errorMessage: string = '';

  constructor(private registerService: RegisterService, private router: Router) { }

  async onRegister() {
    this.errorMessage = '';
  
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passRegex = /^(?!.*\s)(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()?\-._]).{8,24}$/;
  
    if (!this.username || !this.email || !this.password || !this.cpassword || !this.dateOfBirth) {
      this.errorMessage = 'Please fill in all fields!';
      return;
    }
  
    if (!emailRegex.test(this.email)) {
      this.errorMessage = 'Invalid email address!';
      return;
    }
  
    if (!passRegex.test(this.password)) {
      this.errorMessage = `Invalid password. Must be 8-24 characters, contain an uppercase letter, a number, and a special character.`;
      return;
    }
  
    if (this.password !== this.cpassword) {
      this.errorMessage = 'passwords do not match!';
      return;
    }
  
    if(new Date(this.dateOfBirth) >= this.registerService.minDate) {
      this.errorMessage = 'You are too young to play this game!';
      return;
    }
  
    try {

      await this.registerService.registerUser(
        this.username,
        this.email,
        this.password,
        this.dateOfBirth.toString(),
      );

      alert('Registration successful!');
      this.router.navigate(['/login'])

      this.username = '';
      this.email = '';
      this.password = '';
      this.cpassword = '';
      this.dateOfBirth = new Date('1111-01-01');
    } catch(error) {
      if (error instanceof Error) {
        this.errorMessage = error.message;
      } else {
        this.errorMessage = 'Registration failed! Please try again.';
      }
    }
  }
}
