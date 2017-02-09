using ComicStock.Domain;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;


namespace ComicStock.Data
{
    internal class SquareEyesContext : DbContext
    {
        public SquareEyesContext() : base("name=SquareEyes")
        {

        }

        public virtual DbSet<Issue> Issues { get; set; }
        public virtual DbSet<Order> Orders { get; set; }
        public virtual DbSet<Stock> Stock { get; set; }
        public virtual DbSet<Supplier> Suppliers { get; set; }
        public virtual DbSet<SupplierPayment> SupplierPayments { get; set; }
        public virtual DbSet<SupplierQuote> SupplierQuote { get; set; }
        public virtual DbSet<Voucher> Vouchers { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<SupplierQuote>()
                .HasKey(e => new
                {
                    e.IssueID,
                    e.SupplierID,
                    e.QuoteID
                })
                .Property(e => e.QuoteID).HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);
        }
    }
}
