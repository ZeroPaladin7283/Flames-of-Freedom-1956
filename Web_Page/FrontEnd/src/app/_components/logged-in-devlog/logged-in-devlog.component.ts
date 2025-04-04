import { Component, OnInit } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FooterComponent } from '../footer/footer.component';
import { DevLogsService } from '../../_services/dev-logs.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-logged-in-devlog',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, CommonModule],
  templateUrl: './logged-in-devlog.component.html',
  styleUrl: './logged-in-devlog.component.css'
})
export class LoggedInDevlogComponent implements OnInit{
  logList: any[] = [];
  gitUsers: { [key: number]: any } = {};
  showAllLogs = false;

  constructor(private devLogsService: DevLogsService) {}

  ngOnInit(): void {
    this.devLogsService.fetchLogsData().then((data) => {
      this.logList = data;

      this.loadGitUsers();
    });
  }

  loadGitUsers(): void {
    const adminIds = Array.from(new Set(this.logList.map(log => log.adminId)));
    adminIds.forEach((adminId) => {
      this.devLogsService.fetchGitUser(adminId).then((userData) => {
        this.gitUsers[adminId] = userData;
      });
    });
  }

  toggleLogs() {
    this.showAllLogs = !this.showAllLogs;
  }
}
