import { Component } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { ContactService } from '../../_services/contact.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-logged-in-contact',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, FormsModule, CommonModule],
  templateUrl: './logged-in-contact.component.html',
  styleUrl: './logged-in-contact.component.css'
})
export class LoggedInContactComponent {
  email: string = '';
  receiver: string = 'FlamesOfFreedom1956@gmail.com';
  ccMe: boolean = false;
  subject: string = '';
  content: string = '';
  errorMessage: string = '';

  constructor(private contactService: ContactService) {}

  isFormValid(): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!this.email || !this.subject || !this.content) {
      return false;
    }

    if (this.email && !emailRegex.test(this.email)) {
      return false;
    }
  
    return true;
  }

  async onSubmit() {
    this.errorMessage = '';
  
    if (!this.isFormValid()) {
      this.errorMessage = 'Please fill in all fields correctly!';
      return;
    }
  
    console.log({
      email: this.email,
      to: this.receiver,
      ccMe: this.ccMe,
      subject: this.subject,
      content: this.content
    });
  
    try {
      await this.contactService.contactSupport(this.email, this.receiver, this.ccMe, this.subject, this.content);
      alert('Message sent successfully!');
      this.email = '';
      this.subject = '';
      this.content = '';
    } catch (error) {
      console.error('Error:', error);
      this.errorMessage = 'Failed to send the message. Please try again later.';
    }
  }
}
