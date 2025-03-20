import { Component, OnInit} from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { LoginService } from '../../_services/login.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  loginForm!: FormGroup;
  showPassword: boolean = false;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  async onLogin(): Promise<void> {
    this.errorMessage = '';
  
    if (this.loginForm.invalid) {
      this.errorMessage = 'Please fill in all fields!';
      return;
    }
  
    try {
      const { email, password } = this.loginForm.value;
      const user = await this.loginService.login(email, password);
  
      alert('Login successful!');
      this.router.navigate(['/logged-in-home']);
    } catch (error) {
      console.error('Error logging in:', error);
      this.errorMessage = 'Invalid email or password!';
    }
  }

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  getEmailClass() {
    return this.email?.touched && this.email?.invalid && !this.password?.value ? 'invalid' : '';
  }

  getPasswordClass() {
    return this.password?.touched && this.password?.invalid && !this.email?.value ? 'invalid' : '';
  }
}

