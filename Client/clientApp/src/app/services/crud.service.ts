import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { StringMap } from '@angular/compiler/src/compiler_facade_interface';


interface User {
  name:string,
  email:string
}

@Injectable({
  providedIn: 'root'
})
export class CrudService {

  private API_URL: string = "http://localhost:8080/spring5-mvc-hibernate"

  constructor(
    private http: HttpClient
  ) { }

  getUsers(){
    return this.http.get(`${this.API_URL}/usersNg`);
  }

  insertUser(name: string, email: string) {

    const params = new HttpParams()
      .set('name', name)
      .set("email",email);

    return this.http.post(`${this.API_URL}/addUserNg`,params);
  }

  updateUser(id:number, name: string, email: string) {

    const user: User = {
      name:name,
      email:email
    }

    return this.http.put(`${this.API_URL}/updateUserNg/${id}`,user);
  }

  deleteUser(id: number) {
    return this.http.delete(`${this.API_URL}/deleteUserNg/${id}`);
  }


}
