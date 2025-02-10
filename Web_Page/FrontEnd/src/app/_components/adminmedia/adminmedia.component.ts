import { Component } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { LoggedinfooterComponent } from '../loggedinfooter/loggedinfooter.component';

@Component({
  selector: 'app-adminmedia',
  standalone: true,
  imports: [AdminnavbarComponent, LoggedinfooterComponent],
  templateUrl: './adminmedia.component.html',
  styleUrl: './adminmedia.component.css'
})
export class AdminmediaComponent {

}
