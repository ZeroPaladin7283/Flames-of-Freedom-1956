import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from '../footer/footer.component';
import { DevLogsService } from '../../_services/dev-logs.service';

@Component({
  selector: 'app-dev-logs',
  standalone: true,
  imports: [NavbarComponent, FooterComponent],
  templateUrl: './dev-logs.component.html',
  styleUrl: './dev-logs.component.css'
})
export class DevLogsComponent implements OnInit{
  gitUsers: { [key: string]: any } = {};

  constructor(private devLogsService: DevLogsService) {}

  ngOnInit(): void {
    Promise.all([
      this.devLogsService.fetchGitUser('PalcsiFerencKolos'),
      this.devLogsService.fetchGitUser('ZeroPaladin7283')
    ]).then(users => {
      this.gitUsers['admin1'] = users[0];
      this.gitUsers['admin2'] = users[1];
    });
  }
}
