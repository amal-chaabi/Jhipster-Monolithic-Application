import { IMedicalRepport } from 'app/shared/model/medical-repport.model';

export interface IPatient {
  id?: number;
  lastname?: string;
  firstname?: string;
  confidentialityAgreement?: string;
  age?: number;
  hometown?: string;
  adress?: string;
  socialSecurityNumber?: string;
  phonenumber?: string;
  patientMedicalrepports?: IMedicalRepport[];
}

export class Patient implements IPatient {
  constructor(
    public id?: number,
    public lastname?: string,
    public firstname?: string,
    public confidentialityAgreement?: string,
    public age?: number,
    public hometown?: string,
    public adress?: string,
    public socialSecurityNumber?: string,
    public phonenumber?: string,
    public patientMedicalrepports?: IMedicalRepport[]
  ) {}
}
