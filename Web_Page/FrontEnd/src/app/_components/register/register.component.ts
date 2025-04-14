import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { RegisterService } from '../../_services/register.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SimpleModalComponent } from "../simple-modal/simple-modal.component";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, FormsModule, CommonModule, SimpleModalComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  email: string = '';
  password: string = '';
  cpassword: string = '';
  dateOfBirth: Date | null = null;
  errorMessage: string = '';
  ccMe: boolean = false;

  isModalVisible = false;

  showModal() {
    this.isModalVisible = true;
    }

  hideModal() {
    this.isModalVisible = false;
    }

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
      this.errorMessage = 'Passwords do not match!';
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
      this.password = '';
      this.cpassword = '';
      this.dateOfBirth = null;
    } catch(error) {
      if (error instanceof Error) {
        this.errorMessage = error.message;
      } else {
        this.errorMessage = 'Registration failed! Please try again.';
      }
    }

    try {
      await this.registerService.sendRegEmail(this.email, this.ccMe);
      alert('Message sent successfully!');
      this.email = '';
    } catch(error) {
      console.error('Error:', error);
      this.errorMessage = 'Failed to send message. Please try again later.';
    }
  }

  isRegisterEnabled(): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passRegex = /^(?!.*\s)(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()?\-._]).{8,24}$/;

    if (!this.username || !this.email || !this.password || !this.cpassword || !this.dateOfBirth || this.dateOfBirth == null) {
      return false;
    }

    if (this.email && !emailRegex.test(this.email)) {
      return false;
    }

    if (this.password && !passRegex.test(this.password)) {
      return false;
    }

    if(this.cpassword != this.password) {
      return false;
    }

    return true;
  }
}
