import { Component } from '@angular/core';
import { CrudService } from './services/crud.service';

export interface User {
  name: string;
  position: number;
  email: string;
}
const ELEMENT_DATA: User[] = [
  { position: 0, name: 'Loading Info', email: "Loading Info"}
];
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'clientApp';
  public users = [];
  displayedColumns: string[] = ['id', 'name', 'email', 'delete', 'update'];
  dataSource: any = ELEMENT_DATA;

  constructor(
    private crudService: CrudService
  ){}

  ngOnInit(){
    this.getUsers();
  }

  getUsers(){
    this.crudService.getUsers().subscribe(
      (changes)=>{
        console.log("getUsers",changes)
        this.dataSource = changes
      }
    );
  }

  insertUser(){
    let name: HTMLInputElement | null = document.getElementById("name") as HTMLInputElement
    let email: HTMLInputElement | null = document.getElementById("email") as HTMLInputElement
    console.log("valuee", name.value,email.value)
    this.crudService.insertUser(name.value, email.value).subscribe(data => {
      console.log("insertUser", data)
    });
    window.location.reload();

  }

  deleteUser(id: number){
    this.crudService.deleteUser(id).subscribe(data => {
      console.log("deleteUser", data)
    });
    window.location.reload();

  }

  updateUser(id: number){
    let name: HTMLInputElement | null = document.getElementById("InputName"+id) as HTMLInputElement
    let email: HTMLInputElement | null = document.getElementById("InputEmail"+id) as HTMLInputElement
    let nameval = name.value;
    let emailval = email.value;
    console.log("updateUser", nameval, emailval);

    this.crudService.updateUser(id, nameval, emailval).subscribe(data => {
      console.log("updateUser", data)
    });
    window.location.reload();
  }
}
