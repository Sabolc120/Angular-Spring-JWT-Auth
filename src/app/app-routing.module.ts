import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/_auth/auth.guard';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  {path: 'home', component:HomeComponent},
  {path: 'admin', component:AdminComponent, canActivate:[AuthGuard], data:{authorities:['ADMIN']}},
  {path: 'user', component:UserComponent, canActivate:[AuthGuard], data:{authorities:['USER']}},
  {path: 'login', component:LoginComponent},
  {path: 'forbidden', component:ForbiddenComponent},
  {path: 'register', component:RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
