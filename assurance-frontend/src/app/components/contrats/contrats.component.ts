import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { AssuranceService } from '../../services/assurance.service';

@Component({
  selector: 'app-contrats',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './contrats.component.html',
  styleUrl: './contrats.component.css'
})
export class ContratsComponent implements OnInit {
  contrats: any[] = [];
  clients: any[] = [];
  erreur = '';

  contrat = {
    typeContrat: 'AUTOMOBILE',
    clientId: null as number | null,
    dateSouscription: new Date().toISOString().slice(0, 10),
    statut: 'EN_COURS',
    montantCotisation: 0,
    dureeContrat: 12,
    tauxCouverture: 80,
    numeroImmatriculation: '',
    marqueVehicule: '',
    modeleVehicule: '',
    typeLogement: 'APPARTEMENT',
    adresse: '',
    superficie: 0,
    niveauCouverture: 'BASIQUE',
    nombrePersonnesCouvertes: 1
  };

  constructor(
    private service: AssuranceService,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit() {
    this.chargerClients();
    this.chargerContrats();
  }

  chargerClients() {
    this.service.getClients().subscribe({
      next: data => this.clients = data,
      error: () => this.erreur = 'Impossible de charger les clients.'
    });
  }

  chargerContrats() {
    this.service.getContrats().subscribe({
      next: data => this.contrats = data,
      error: () => this.erreur = 'Impossible de charger les contrats.'
    });
  }

  enregistrerContrat() {
    if (!this.contrat.clientId) {
      this.erreur = 'Selectionnez un client.';
      return;
    }

    const request = this.contrat.typeContrat === 'AUTOMOBILE'
      ? this.service.createContratAutomobile(this.contrat)
      : this.contrat.typeContrat === 'HABITATION'
        ? this.service.createContratHabitation(this.contrat)
        : this.service.createContratSante(this.contrat);

    request.subscribe({
      next: () => {
        this.erreur = '';
        this.chargerContrats();
      },
      error: () => this.erreur = 'Creation refusee. Utilisez admin ou employe.'
    });
  }

  supprimerContrat(id: number) {
    if (confirm('Supprimer ce contrat ?')) {
      this.service.deleteContrat(id).subscribe({
        next: () => this.chargerContrats(),
        error: () => this.erreur = 'Suppression refusee. Seul admin peut supprimer.'
      });
    }
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
