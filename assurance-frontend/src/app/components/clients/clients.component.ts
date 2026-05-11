import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AssuranceService } from '../../services/assurance.service';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent implements OnInit {
  clients: any[] = [];
  nouveauClient = { nom: '', email: '' };
  clientEnEdition: any | null = null;
  erreur = '';

  constructor(
    private service: AssuranceService,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.chargerClients();
  }

  chargerClients() {
    this.service.getClients().subscribe({
      next: data => this.clients = data,
      error: () => this.erreur = 'Impossible de charger les clients. Verifiez le backend et le token.'
    });
  }

  ajouterClient() {
    if (!this.nouveauClient.nom || !this.nouveauClient.email) {
      this.erreur = 'Le nom et email sont obligatoires.';
      return;
    }
    this.service.createClient(this.nouveauClient).subscribe({
      next: () => {
        this.chargerClients();
        this.nouveauClient = { nom: '', email: '' };
        this.erreur = '';
      },
      error: () => this.erreur = 'Creation refusee. Utilisez admin ou employe.'
    });
  }

  modifierClient(client: any) {
    this.clientEnEdition = { ...client };
  }

  enregistrerModification() {
    if (!this.clientEnEdition) {
      return;
    }
    this.service.updateClient(this.clientEnEdition.id, this.clientEnEdition).subscribe({
      next: () => {
        this.clientEnEdition = null;
        this.chargerClients();
      },
      error: () => this.erreur = 'Modification refusee. Utilisez admin ou employe.'
    });
  }

  annulerModification() {
    this.clientEnEdition = null;
  }

  supprimerClient(id: number) {
    if (confirm('Supprimer ce client ?')) {
      this.service.deleteClient(id).subscribe({
        next: () => this.chargerClients(),
        error: () => this.erreur = 'Suppression refusee. Seul admin peut supprimer.'
      });
    }
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
