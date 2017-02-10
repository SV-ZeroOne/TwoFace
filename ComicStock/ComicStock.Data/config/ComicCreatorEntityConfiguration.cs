using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.config
{
    class ComicCreatorEntityConfiguration : EntityTypeConfiguration<ComicCreator>
    {
        public ComicCreatorEntityConfiguration()
        {
            this.HasKey(cc => new
            {
                cc.IssueID,
                cc.CreatorID
            });
        }
    }
}
