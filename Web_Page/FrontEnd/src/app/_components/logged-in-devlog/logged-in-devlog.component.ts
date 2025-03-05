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

  constructor(private devLogsService: DevLogsService) {}

  ngOnInit(): void {
    this.devLogsService.fetchLogsData().then((data) => {
      this.logList = data;

      this.loadGitUsers();
    });
  }

  loadGitUsers(): void {
    const userIds = Array.from(new Set(this.logList.map(log => log.userId)));
    userIds.forEach((userId) => {
      this.devLogsService.fetchGitUser(userId).then((userData) => {
        this.gitUsers[userId] = userData;
      });
    });
  }
}
