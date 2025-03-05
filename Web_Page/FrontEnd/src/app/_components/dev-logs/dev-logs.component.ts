import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from '../footer/footer.component';
import { DevLogsService } from '../../_services/dev-logs.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dev-logs',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent],
  templateUrl: './dev-logs.component.html',
  styleUrl: './dev-logs.component.css'
})
export class DevLogsComponent implements OnInit{
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
