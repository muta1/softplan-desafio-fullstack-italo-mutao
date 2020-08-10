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
