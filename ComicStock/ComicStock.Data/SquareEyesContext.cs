﻿using ComicStock.Domain;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;


namespace ComicStock.Data
{
    internal class SquareEyesContext : DbContext
    {
        public SquareEyesContext()
        
            #if DEBUG
            : base("name=SquareEyes")
#else
            : base("name=SquareEyesRelease")
#endif
        { }

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

        }
    }
}
