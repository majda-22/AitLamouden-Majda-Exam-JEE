import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class AssuranceService {
  constructor(private http: HttpClient, private auth: AuthService) {}

  private headers() {
    const token = this.auth.getToken();
    return new HttpHeaders(token ? { Authorization: `Bearer ${token}` } : {});
  }

  getClients() {
    return this.http.get<any[]>('/api/clients', { headers: this.headers() });
  }

  createClient(client: any) {
    return this.http.post('/api/clients', client, { headers: this.headers() });
  }

  updateClient(id: number, client: any) {
    return this.http.put(`/api/clients/${id}`, client, { headers: this.headers() });
  }

  deleteClient(id: number) {
    return this.http.delete(`/api/clients/${id}`, { headers: this.headers() });
  }

  getContrats() {
    return this.http.get<any[]>('/api/contrats', { headers: this.headers() });
  }

  getContratsByClient(clientId: number) {
    return this.http.get<any[]>(`/api/contrats/client/${clientId}`, { headers: this.headers() });
  }

  createContratAutomobile(contrat: any) {
    return this.http.post('/api/contrats/automobile', contrat, { headers: this.headers() });
  }

  createContratHabitation(contrat: any) {
    return this.http.post('/api/contrats/habitation', contrat, { headers: this.headers() });
  }

  createContratSante(contrat: any) {
    return this.http.post('/api/contrats/sante', contrat, { headers: this.headers() });
  }

  updateContrat(id: number, typeContrat: string, contrat: any) {
    const type = (typeContrat || '').toLowerCase();
    if (type === 'automobile') {
      return this.http.put(`/api/contrats/automobile/${id}`, contrat, { headers: this.headers() });
    }
    if (type === 'habitation') {
      return this.http.put(`/api/contrats/habitation/${id}`, contrat, { headers: this.headers() });
    }
    if (type === 'sante') {
      return this.http.put(`/api/contrats/sante/${id}`, contrat, { headers: this.headers() });
    }
    return this.http.put(`/api/contrats/${id}`, contrat, { headers: this.headers() });
  }

  deleteContrat(id: number) {
    return this.http.delete(`/api/contrats/${id}`, { headers: this.headers() });
  }

  getPaiementsByContrat(contratId: number) {
    return this.http.get<any[]>(`/api/paiements/contrat/${contratId}`, { headers: this.headers() });
  }
}
