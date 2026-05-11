import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username = 'admin';
  password = 'admin123';
  erreur = '';
  loading = false;

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    this.erreur = '';
    this.loading = true;
    this.auth.login(this.username, this.password).subscribe({
      next: (token: string) => {
        this.auth.saveToken(token);
        this.router.navigate(['/clients']);
      },
      error: () => {
        this.erreur = 'Identifiants incorrects ou backend indisponible.';
        this.loading = false;
      }
    });
  }
}
