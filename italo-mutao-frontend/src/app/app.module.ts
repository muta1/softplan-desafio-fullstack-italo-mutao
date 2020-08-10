import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";

import { AppComponent } from "./app.component";
import { appRoutingModule } from "./app.routing";

import { JwtInterceptor, ErrorInterceptor } from "./_helpers";
import { HomeComponent } from "./home";
import { AdminComponent } from "./admin";
import { LoginComponent } from "./login";
import { ScreeningComponent } from "./screening";
import { FinisherComponent } from "./finisher";
import { UserCreateComponent } from "./admin/components/user-create/user-create.component";
import { UserEditComponent } from "./admin/components/user-edit/user-edit.component";
import { UserListComponent } from "./admin/components/user-list/user-list.component";
@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    appRoutingModule,
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AdminComponent,
    ScreeningComponent,
    FinisherComponent,
    UserCreateComponent,
    UserEditComponent,
    UserListComponent,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
