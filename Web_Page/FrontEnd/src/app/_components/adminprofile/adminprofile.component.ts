import { Component } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../footer/footer.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-adminprofile',
  standalone: true,
  imports: [AdminnavbarComponent ,FooterComponent, FormsModule],
  templateUrl: './adminprofile.component.html',
  styleUrl: './adminprofile.component.css'
})
export class AdminprofileComponent {
  profileUser:string = "";
  profileEmail:string = "";
  profilePass:string = "";
  profileDesc:string = "";

  constructor(private router: Router){}

  onLogout() {
    this.router.navigate(['home']);
  }
}
