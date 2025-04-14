import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from '../../_services/profile.service';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, FormsModule, CommonModule],
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  newUsername: string = "";
  newEmail: string = "";
  newPassword: string = "";
  imageIn: string = "";
  profilePic: string = "";

  currentUsername: string = "";
  currentEmail: string = "";
  currentPassword: string = "";

  errorMessage: string = "";

  constructor(private router: Router, private profileService: ProfileService) {}

  ngOnInit(): void {
    const userId = Number(localStorage.getItem('userId'));

    if (!userId) {
      console.warn('UserID not found in localstorage');
      return;
    }

    this.profileService.fetchUser(userId).then((data) => {
      if (data && data?.result) {
        this.currentUsername = data.result.username;
        this.currentEmail = data.result.email;
        this.currentPassword = data.result.password;
        if (data && data?.result?.profilePic) {
          this.profilePic = data.result.profilePic;
        }
      }
    });
  }

  async saveChanges() {
    const userId = Number(localStorage.getItem('userId'));
    if (!userId) {
      console.warn('UserID not found in localstorage');
      return;
    }

    try {
      const data = {
        id: userId,
        newUsername: this.newUsername || this.currentUsername,
        newEmail: this.newEmail || this.currentEmail,
        newPassword: this.newPassword || this.currentPassword,
        imageIn: this.imageIn || this.profilePic,
      };
      await this.profileService.changeInfo(
        data.id,
        data.newUsername,
        data.newEmail,
        data.newPassword,
        data.imageIn
      );

      alert('Data successfully updated!');
      this.router.navigate(['/home']);

      this.newUsername = '';
      this.newEmail = '';
      this.newPassword = '';
    } catch (error) {
      if (error instanceof Error) {
        this.errorMessage = error.message;
      } else {
        this.errorMessage = 'Failed to update user information. Please try again.';
      }
    }
  }

  onFileSelected(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      this.toBase64(file).then((base64Image) => {
        this.imageIn = base64Image;
        this.profilePic = base64Image;
      });
    }
  }

  toBase64(file: File): Promise<string> {
    return new Promise((resolve, reject) => {
      let reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result!.toString().split(",")[1]);
      reader.onerror = (error) => reject(error);
    });
  } 

  onLogout() {
    this.router.navigate(['home']);
    localStorage.removeItem('loggedInUser');
    localStorage.removeItem('userId');
    localStorage.removeItem('isAdmin');
  }

  isSaveEnabled(): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passRegex = /^(?!.*\s)(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()?\-._]).{8,24}$/;

    if (!this.newUsername && !this.newEmail && !this.newPassword && !this.imageIn) {
      return false;
    }

    if (this.newEmail && !emailRegex.test(this.newEmail)) {
      return false;
    }

    if (this.newPassword && !passRegex.test(this.newPassword)) {
      return false;
    }

    return true;
  }
}
