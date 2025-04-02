import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalService } from '../../_services/modal.service';

@Component({
  selector: 'app-simple-modal',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './simple-modal.component.html',
  styleUrl: './simple-modal.component.css'
})
export class SimpleModalComponent {
  email: string = "";
  ccMe: boolean = false;
  errorMessage: string = "";
  verificationCodeSent: boolean = false;
  verificationCode: string = "";
  @Output() close = new EventEmitter<void>();

  closeModal(): void {
    this.close.emit();
  }

  constructor(private modalService: ModalService) {}

  async sendCode() {
    this.errorMessage = '';
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  
    if (!this.email || !emailRegex.test(this.email)) {
      this.errorMessage = 'Invalid email address!';
      return;
    }
  
    try {
      const responseData = await this.modalService.sendTemporaryPass(this.email, this.ccMe);
      if (!responseData || !responseData.password) {
        throw new Error('Temporary password was not received from the API.');
      }
      await this.modalService.changePassword(this.email, responseData.password);
      console.log('Password updated successfully in the database');
      this.email = '';
    } catch (error) {
      console.error('Error:', error);
      this.errorMessage = 'Failed to send message or update password. Please try again later.';
    }
  }
}
