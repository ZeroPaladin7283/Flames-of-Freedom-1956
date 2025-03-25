import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-success-popup',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './success-popup.component.html',
  styleUrl: './success-popup.component.css'
})
export class SuccessPopupComponent {
  @Output() close = new EventEmitter<void>();

  closePopup() {
    this.close.emit();
  }
}
