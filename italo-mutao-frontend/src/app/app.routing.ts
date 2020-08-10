import { ProcessTechnicalOpinionComponent } from "./screening/components/process-technical-opinion/process-technical-opinion.component";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./home";
import { AdminComponent } from "./admin";
import { LoginComponent } from "./login";
import { AuthGuard } from "./_helpers";
import { Role } from "./_models";
import { ScreeningComponent } from "./screening";
import { FinisherComponent } from "./finisher";
import { UserCreateComponent } from "./admin/components/user-create/user-create.component";
import { UserEditComponent } from "./admin/components/user-edit/user-edit.component";
import { UserListComponent } from "./admin/components/user-list/user-list.component";
import { ProcessListComponent } from "./screening/components/process-list/process-list.component";
import { ProcessEditComponent } from "./screening/components/process-edit/process-edit.component";
import { ProcessCreateComponent } from "./screening/components/process-create/process-create.component";

const routes: Routes = [
  {
    path: "",
    component: HomeComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "admin",
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Admin] },
  },
  {
    path: "user-create",
    component: UserCreateComponent,
    data: { roles: [Role.Admin] },
  },
  {
    path: "user-edit/:id",
    component: UserEditComponent,
    data: { roles: [Role.Admin] },
  },
  {
    path: "user-list",
    component: UserListComponent,
    data: { roles: [Role.Admin] },
  },
  {
    path: "screening",
    component: ScreeningComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Screening] },
  },
  {
    path: "process-create",
    component: ProcessCreateComponent,
    data: { roles: [Role.Screening] },
  },
  {
    path: "process-edit/:id",
    component: ProcessEditComponent,
    data: { roles: [Role.Screening] },
  },
  {
    path: "process-list",
    component: ProcessListComponent,
    data: { roles: [Role.Screening] },
  },
  {
    path: "process-technical-opinion/:id",
    component: ProcessTechnicalOpinionComponent,
    data: { roles: [Role.Screening] },
  },
  {
    path: "finisher",
    component: FinisherComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Finisher] },
  },
  {
    path: "login",
    component: LoginComponent,
  },

  // otherwise redirect to home
  { path: "**", redirectTo: "" },
];

export const appRoutingModule = RouterModule.forRoot(routes);
