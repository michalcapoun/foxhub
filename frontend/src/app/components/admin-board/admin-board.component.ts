import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {ApiService} from "../../_services/api/api.service";
import {AuthService} from "../../_services/auth.service";

@Component({
  selector: 'app-admin-board',
  templateUrl: './admin-board.component.html',
  styleUrls: ['./admin-board.component.css']
})
export class AdminBoardComponent implements OnInit{
  form: any = {
    email: null,
  };

  // @ts-ignore
  users: User[] = [];
  selectedUser: User = {password: "", firstName: "", lastName: "", email: ""};

  constructor(private apiService:ApiService,private authService: AuthService) {
  }

  ngOnInit(): void {
    this.apiService.getAll().subscribe(users => {
      this.users=users;
      this.selectedUser = users[0];
    });
  }

  deleteUser(user:User) {
    this.apiService.removeUser(user.nickname)
  }

  setSelectedUser(user: User) {
    this.selectedUser = user;
  }

  changeRole(user: User) {
    this.apiService.changeRole(user.nickname)

  }

  onSubmit() {
    const {  email  } = this.form;

    this.authService.invite(email).subscribe({
      next: data => {
        this.form.email = null;
      },
      error: err => {
      }
    });


  }
}
