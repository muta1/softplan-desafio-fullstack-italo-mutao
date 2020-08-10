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
import { IonicModule } from "@ionic/angular";
import { ProcessCreateComponent } from "./screening/components/process-create/process-create.component";
import { ProcessEditComponent } from "./screening/components/process-edit/process-edit.component";
import { ProcessListComponent } from "./screening/components/process-list/process-list.component";
import { ProcessTechnicalOpinionComponent } from "./screening/components/process-technical-opinion/process-technical-opinion.component";;
import { TechnicalCreateComponent } from './finisher/components/technical-create/technical-create.component'
;
import { TechnicalEditComponent } from './finisher/components/technical-edit/technical-edit.component'
;
import { TechnicalListComponent } from './finisher/components/technical-list/technical-list.component'@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    appRoutingModule,
    IonicModule.forRoot(),
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
    ProcessCreateComponent,
    ProcessEditComponent,
    ProcessListComponent,
    ProcessTechnicalOpinionComponent,,
    TechnicalCreateComponent
,
    TechnicalEditComponent ,
    TechnicalListComponent ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
