import { Component } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { LoggedinfooterComponent } from '../loggedinfooter/loggedinfooter.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-adminprofile',
  standalone: true,
  imports: [AdminnavbarComponent ,LoggedinfooterComponent, FormsModule],
  templateUrl: './adminprofile.component.html',
  styleUrl: './adminprofile.component.css'
})
export class AdminprofileComponent {
  profileUser:string = "";
  profileEmail:string = "";
  profilePass:string = "";
  profileDesc:string = "";
}
