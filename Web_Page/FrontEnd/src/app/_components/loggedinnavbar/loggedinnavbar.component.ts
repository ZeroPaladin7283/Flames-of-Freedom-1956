import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NavbarService } from '../../_services/navbar.service';

@Component({
  selector: 'app-loggedinnavbar',
  templateUrl: './loggedinnavbar.component.html',
  styleUrls: ['./loggedinnavbar.component.css'],
  standalone: true,
})
export class LoggedinnavbarComponent implements OnInit {
  profilePic: string = '';
  isAdmin: boolean = false;

  constructor(private router: Router, private navbarService: NavbarService) {}

  ngOnInit(): void {
    const userId = Number(localStorage.getItem('userId'));
    this.isAdmin = localStorage.getItem('isAdmin') === 'true';

    if (!userId) {
      console.warn('UserID not found in localstorage');
      return;
    }

    this.navbarService.fetchUser(userId).then((data) => {
      if (data && data?.result) {
        this.profilePic = data.result.profilePic;
      }
    });
  }
}
