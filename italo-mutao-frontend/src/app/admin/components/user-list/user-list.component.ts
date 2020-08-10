import { Component, OnInit } from "@angular/core";
import { UserService } from "@app/_services";
import { AlertController, LoadingController } from "@ionic/angular";
import { RoleTranslator } from "@app/_models";

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
})
export class UserListComponent implements OnInit {
  users: any = [];

  constructor(
    private apiService: UserService,
    private alert: AlertController,
    private loading: LoadingController
  ) {
    this.readUser();
  }

  ngOnInit() {}

  translator(role: string): string {
    return RoleTranslator.translate(role);
  }

  readUser() {
    this.apiService.getUsers().subscribe((data) => {
      this.users = data;
    });
  }

  async removeUser(user, index) {
    let alert = await this.alert.create({
      header: "Aviso",
      message: `Deseja realmente remover usuário: ${user.name} ?`,
      buttons: [
        {
          text: "Sim",
          handler: async () => {
            console.log("deletando usuário");
            const loading = await this.loading.create({ spinner: "circles" });
            await loading.present();
            let deletedUser = await this.apiService
              .deleteUser(user.id)
              .toPromise();
            console.log("deletedUser: ", deletedUser);

            this.users.splice(index, 1);
            await loading.dismiss();
          },
        },
        {
          text: "Não",
        },
      ],
    });
    await alert.present();
  }
}
