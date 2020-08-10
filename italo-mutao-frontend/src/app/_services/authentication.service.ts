import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";

import { environment } from "@environments/environment";
import { User, ApiResponse } from "@app/_models";
import { ToastController } from "@ionic/angular";

@Injectable({ providedIn: "root" })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient, private toast: ToastController) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem("currentUser"))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(name: string, password: string) {
    return this.http
      .post<ApiResponse<User>>(`${environment.apiUrl}/login`, {
        name,
        password,
      })
      .pipe(
        map(async (api: ApiResponse<User>) => {
          if (api.response && api.response.token) {
            localStorage.setItem("currentUser", JSON.stringify(api.response));
            this.currentUserSubject.next(api.response);
            const toastSucceed = await this.toast.create({
              message: "Seja bem vindo!",
              duration: 3000,
            });
            await toastSucceed.present();
          } else {
            localStorage.clear();
            this.currentUserSubject.next(null);
            const toastFailure = await this.toast.create({
              message: "Credenciais inválidas!",
              duration: 3000,
            });
            await toastFailure.present();
          }
          return api.response;
        })
      );
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.clear();
    this.currentUserSubject.next(null);
  }
}
