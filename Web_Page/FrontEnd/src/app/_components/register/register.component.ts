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
    if(!this.registerUsername || !this.registerEmail || !this.registerPass || this.registerCPass || !this.registerBDate) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    const parsedBDate = new Date(this.registerBDate);
    if(isNaN(parsedBDate.getTime())) {
      this.errorMessage = "Invalid birtday format!";
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

      this.registerUsername = "";
      this.registerEmail = "";
      this.registerPass = "";
      this.registerCPass = "";
      this.registerBDate = "";

      alert("Successfully registerd!");
    } else {
      this.errorMessage = "Invalid username / email / birth date.";
    }
  }
}
