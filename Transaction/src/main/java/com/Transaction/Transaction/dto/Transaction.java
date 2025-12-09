@Entity
@Table(name = "transactions")
public class Transaction {
  @Id 
  @GeneratedValue
  private Long id;
  private String type; // VIREMENT, DEPOT, RETRAIT, PAIEMENT
  private Long compteSourceId;
  private Long compteDestinationId;
  private BigDecimal montant;
  private LocalDateTime dateCreation;
  private String description;
 
}
