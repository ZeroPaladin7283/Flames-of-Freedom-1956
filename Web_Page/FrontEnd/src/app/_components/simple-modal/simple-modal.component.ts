import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-simple-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './simple-modal.component.html',
  styleUrl: './simple-modal.component.css'
})
export class SimpleModalComponent {
  @Output() close = new EventEmitter<void>();

  closeModal(): void {
	this.close.emit();
  }
}
