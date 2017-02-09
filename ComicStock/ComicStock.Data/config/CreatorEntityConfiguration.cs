using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.config
{
    class CreatorEntityConfiguration : EntityTypeConfiguration<Creator>
    {
        public CreatorEntityConfiguration()
        {
            this.Ignore(c => c.CreatorID);
            this.HasMany(c => c.ComicCreator)
                .WithRequired(c => c.Creator);
        }
    }
}
